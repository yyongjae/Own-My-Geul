package omg.omgspringbootapp.domain.font.controller

import omg.omgspringbootapp.domain.font.service.FontService
import omg.omgspringbootapp.global.dto.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/font")
class FontController (private val fontService: FontService) {
    @PostMapping("/handwriting")
    fun uploadHandwritingToGCP(
        @RequestParam("image") image: MultipartFile,
        @RequestParam("text") text: String
    ): ResponseEntity<CommonResponse> {
        // GCP 업로드
        fontService.uploadHandwriting(
            image,
            text
        )
        return ResponseEntity.ok(CommonResponse().response("이미지 업로드 성공"))
    }
}