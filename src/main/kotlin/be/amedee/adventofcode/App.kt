package be.amedee.adventofcode

import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

private const val BASE_PACKAGE = "be.amedee.adventofcode"
private const val EXCLUDE_PACKAGE = BASE_PACKAGE

fun main() {
    findAndRunMainMethods(BASE_PACKAGE, EXCLUDE_PACKAGE)
}

private fun findAndRunMainMethods(
    packageName: String,
    excludePackage: String,
) {
    val packageDir = getPackageDir(packageName)
    runMainMethodsInThisPackage(packageDir, packageName, excludePackage)
    runMainMethodsInSubPackages(packageDir, packageName, excludePackage)
}

private fun runMainMethodsInThisPackage(
    packageDir: File,
    packageName: String,
    excludePackage: String,
) = getClassFiles(packageDir)
    .sortedBy { it.nameWithoutExtension }
    .forEach {
        val className = "$packageName.${it.nameWithoutExtension}"
        try {
            val clazz = Class.forName(className)

            when {
                !isInPackage(clazz, excludePackage) ->
                    getMainMethods(clazz)
                        .forEach { mainMethod ->
                            runMainMethod(className, mainMethod)
                        }
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

private fun runMainMethodsInSubPackages(
    packageDir: File,
    packageName: String,
    excludePackage: String,
) = getSubPackages(packageDir)
    .sortedBy { it.name }
    .forEach {
        findAndRunMainMethods("$packageName.${it.name}", excludePackage)
    }

private fun runMainMethod(
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

private fun getMainMethods(clazz: Class<*>) = clazz.methods.filter { isMainMethod(it) }

private fun getPackageDir(packageName: String) =
    File(
        Thread
            .currentThread()
            .contextClassLoader
            .getResource(
                packageName
                    .replace(".", "/"),
            )
            ?.toURI()!!,
    )

private fun getClassFiles(packageDir: File) =
    packageDir.listFiles { file ->
        file.isFile && file.name.endsWith(".class")
    }?.toList() ?: emptyList()

private fun getSubPackages(packageDir: File) =
    packageDir.listFiles { file ->
        file.isDirectory
    }?.toList() ?: emptyList()

private fun isInPackage(
    clazz: Class<*>,
    packageName: String,
) = clazz.`package`?.name == packageName

private fun isMainMethod(method: Method) =
    method.name == "main" &&
        method.parameterCount == 1 &&
        method.parameterTypes[0] == Array<String>::class.java
