package omg.omgspringbootapp.domain.upload

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class UploadController {

    @Value("\${gcs.bucket-name}")
    lateinit var bucketName: String

    @Value("\${gcs.key-file}")
    lateinit var keyFile: String

    @PostMapping("/upload")
    fun uploadImageToGCP(
        @RequestParam("image") image: MultipartFile,
        @RequestParam("text") text: String
    ): String {
        val credentials = GoogleCredentials.fromStream(FileInputStream(keyFile))
        val storage = StorageOptions.newBuilder().setCredentials(credentials).build().service
        val blobId = BlobId.of(bucketName, image.originalFilename)
        val blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(image.contentType)
            .build()

        storage.create(blobInfo, image.bytes)

        return "success!"
    }
}
