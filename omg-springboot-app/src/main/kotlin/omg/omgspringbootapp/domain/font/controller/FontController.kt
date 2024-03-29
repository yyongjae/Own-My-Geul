package omg.omgspringbootapp.domain.font.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import omg.omgspringbootapp.domain.font.service.FontService
import omg.omgspringbootapp.global.dto.response.CommonResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

@RestController
@RequestMapping("/api/v1/font")
class FontController (private val fontService: FontService) {

    // Intellij에 환경변수 등록
    @Value("\${custom.ai-server-url}")
    lateinit var aiServerUrl: String

    @Operation(summary = "폰트 생성", description = "손글씨 이미지를 업로드하면 폰트가 생성됩니다.")
    @Parameter(name = "MultipartFile", description = "손글씨 이미지")
    @Parameter(name = "String", description = "이미지 이름(삭제 예정)")
    @PostMapping("/new")
    fun createFont(
        @RequestParam("handwriting") image: MultipartFile,
        httpServletRequest: MultipartHttpServletRequest
    ): ResponseEntity<CommonResponse> {
        val memberId = httpServletRequest.getAttribute("memberId").toString() ?: ""

        // GCP 이미지 업로드
        fontService.uploadHandwriting(
            image, memberId
        )

//        // 폰트 생성 요청
//        val fontByteArray = fontService.createFont(image, aiServerUrl.plus("/upload"))
//
//        // GCP 폰트 업로드
//        fontService.uploadFont(fontByteArray)

        return ResponseEntity.ok(CommonResponse().response(true,"폰트 업로드 성공"))
    }
}