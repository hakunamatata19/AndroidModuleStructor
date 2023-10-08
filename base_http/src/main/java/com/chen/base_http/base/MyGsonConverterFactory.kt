package com.chen.base_http.base

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

class MyGsonConverterFactory : Converter.Factory() {


    companion object{
        val gson by lazy {
            Gson()
        }
        val instance by lazy {
            MyGsonConverterFactory()
        }
    }

//Type type, Annotation[] annotations, Retrofit retrofit
    override fun responseBodyConverter(
        type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?
    ): Converter<ResponseBody, *> {
        val adapter: TypeAdapter<Any> = gson.getAdapter(TypeToken.get(type) as TypeToken<Any>)
        return MyGsonResponseBodyConverter<Any>(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<Annotation?>?,
        methodAnnotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<*, RequestBody?> {
        val adapter: TypeAdapter<Any> = gson.getAdapter(TypeToken.get(type)as TypeToken<Any>)
        return MyGsonRequestBodyConverter<Any>(gson, adapter)
    }
}

internal class MyGsonResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val jsonReader = gson.newJsonReader(value.charStream())
        return try {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            result
        } finally {
            value.close()
        }
    }
}


internal class MyGsonRequestBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) :
    Converter<T, RequestBody?> {
    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }

    companion object {
        private val MEDIA_TYPE: MediaType = "application/json; charset=UTF-8".toMediaType()
        private val UTF_8 = Charset.forName("UTF-8")
    }
}