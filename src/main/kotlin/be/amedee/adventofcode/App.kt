package be.amedee.adventofcode

import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

fun main() {
    val basePackage = "be.amedee.adventofcode"
    findAndRunMainMethods(basePackage, basePackage)
}

fun findAndRunMainMethods(
    packageName: String,
    excludePackage: String,
) {
    val packageDir = getPackageDir(packageName)

    val sortedClassFiles =
        getClassFiles(packageDir)
            .sortedBy { it.nameWithoutExtension }

    sortedClassFiles.forEach { classFile ->
        val className = packageName + "." + classFile.nameWithoutExtension
        try {
            val clazz = Class.forName(className)

            if (!isInPackage(clazz, excludePackage)) {
                val mainMethods = getMainMethods(clazz)

                mainMethods.forEach { mainMethod ->
                    runMainMethod(className, mainMethod)
                }
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    getSubPackages(packageDir)
        .sortedBy { it.name }
        .forEach { subPackage ->
            val subPackageName = packageName + "." + subPackage.name
            findAndRunMainMethods(subPackageName, excludePackage)
        }
}

fun getPackageDir(packageName: String): File {
    val packagePath = packageName.replace(".", "/")
    val packageUrl = Thread.currentThread().contextClassLoader.getResource(packagePath)
    return File(packageUrl?.toURI()!!)
}

fun getClassFiles(packageDir: File) =
    packageDir.listFiles { file ->
        file.isFile && file.name.endsWith(".class")
    }?.toList() ?: emptyList()

fun getMainMethods(clazz: Class<*>) = clazz.methods.filter { isMainMethod(it) }

fun runMainMethod(
    className: String,
    mainMethod: Method,
) = try {
    println("Running main method in class: $className")
    mainMethod.invoke(null, arrayOf<String>())
    println()
} catch (ex: InvocationTargetException) {
    println("Error invoking main method in class $className:")
    ex.targetException.printStackTrace()
}

fun getSubPackages(packageDir: File) =
    packageDir.listFiles { file ->
        file.isDirectory
    }?.toList() ?: emptyList()

fun isInPackage(
    clazz: Class<*>,
    packageName: String,
) = clazz.`package`?.name == packageName

fun isMainMethod(method: Method) =
    (method.name == "main") &&
        (method.parameterCount == 1) &&
        (method.parameterTypes[0] == Array<String>::class.java)
