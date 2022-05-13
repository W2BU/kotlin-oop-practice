package lab6

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.*
import lab2.shapes.*
import lab2.ColoredShape2D
import lab6.ShapeCollector
import java.io.File
import java.io.IOException

class Serializer {
    private val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(ColoredShape2D::class) {
                subclass(Square::class)
                subclass(Circle::class)
                subclass(Triangle::class)
                subclass(Rectangle::class)
            }
        }
    }

    private val libPrefix: String = "src/main/kotlin/lab6/"

    fun serializeToFile(collector: ShapeCollector<ColoredShape2D>, fileName: String) {
        try {
            File(libPrefix + fileName).writeText(json.encodeToString(collector))
        } catch (e: IOException) {
            println(e.message)
        }
    }

    fun deserializeFromFile(fileName: String): ShapeCollector<ColoredShape2D> {
        require(File(libPrefix + fileName).exists()) { "Specified file does not exist in $libPrefix"}
        return json.decodeFromString(File(libPrefix + fileName).readText())
    }

    fun serializeToJson(collector: ShapeCollector<ColoredShape2D>): String {
        return json.encodeToString(collector)
    }

    fun deserializeFromJson(string: String): ShapeCollector<ColoredShape2D> {
        return json.decodeFromString(string)
    }

}