package omg.omgspringbootapp.domain.font.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import omg.omgspringbootapp.domain.font.exception.FailFontCreationException
import omg.omgspringbootapp.global.exception.OmgException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.ClassPathResource
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
import java.util.UUID

@Service
class FontService {

    @Value("\${gcs.bucket-name}")
    lateinit var bucketName: String

    fun uploadHandwriting(
        image: MultipartFile
    ){
        // 인증을 위한 파일 가져오기
        val resource = ClassPathResource("credentials.json")
        val inputStream = resource.inputStream
        val credentials = GoogleCredentials.fromStream(inputStream)

        // Storage 객체 생성
        val storage = StorageOptions
            .newBuilder()
            .setCredentials(credentials)
            .build().service

        val blobId = BlobId.of(bucketName, image.originalFilename+UUID.randomUUID().toString())
        val blobInfo = BlobInfo
            .newBuilder(blobId)
            .setContentType(image.contentType)
            .build()

        storage.create(blobInfo, image.bytes)
    }

    fun createFont(
        handwriting: MultipartFile,
        url: String
    ): File{
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
            val fontResource = ClassPathResource("/my-font.ttf")
            val file: File = fontResource.file
            FileOutputStream(file).use { stream->
                stream.write(response.body)
            }
            return file
        }
        println("폰트 생성 실패")
        throw FailFontCreationException("폰트 생성에 실패하였습니다.", OmgException.FAIL_FONT_CREATION)
    }

    fun uploadFont(
        font: File
    ){
        // 인증을 위한 파일 가져오기
        val resource = ClassPathResource("credentials.json")
        val inputStream = resource.inputStream
        val credentials = GoogleCredentials.fromStream(inputStream)

        // Storage 객체 생성
        val storage = StorageOptions
            .newBuilder()
            .setCredentials(credentials)
            .build().service

        val fileName = font.name
        val blobInfo = BlobInfo
            .newBuilder(bucketName, fileName)
            .build()

        storage.create(blobInfo, font.readBytes())
    }
}