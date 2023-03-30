package omg.omgspringbootapp.domain.font.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Service
class FontService {

    @Value("\${gcs.bucket-name}")
    lateinit var bucketName: String

    @Value("\${gcs.key-file}")
    lateinit var keyFile: String

    fun uploadHandwriting(
        image: MultipartFile,
        text: String
    ){
        val credentials = GoogleCredentials.fromStream(FileInputStream(keyFile))
        val storage = StorageOptions.newBuilder().setCredentials(credentials).build().service
        val blobId = BlobId.of(bucketName, image.originalFilename)
        val blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(image.contentType)
            .build()

        storage.create(blobInfo, image.bytes)
    }

    fun createFont(
        handwriting: MultipartFile,
        url: String
    ): String{
        // RestTemplate
        val restTemplate = RestTemplate(SimpleClientHttpRequestFactory())
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        // HttpEntity
        val multipart = LinkedMultiValueMap<String, Any>()
        multipart.add("handwriting", ByteArrayResource(handwriting.bytes))
        val requestEntity = HttpEntity(multipart,headers)

        // 요청
        val response = restTemplate.postForEntity(url, requestEntity, ByteArray::class.java)

        // 응답
        if (response.statusCode.is2xxSuccessful) {
            println("폰트 생성 성공")
            val file = File("/Users/maeng/desktop/my-font.ttf")
            FileOutputStream(file).use { stream->
                stream.write(response.body)
            }
            return "success"
        }
        println("폰트 생성 실패")
        return "fail"
    }
}