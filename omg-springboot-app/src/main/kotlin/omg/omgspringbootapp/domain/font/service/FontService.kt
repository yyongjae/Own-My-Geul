package omg.omgspringbootapp.domain.font.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

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
}