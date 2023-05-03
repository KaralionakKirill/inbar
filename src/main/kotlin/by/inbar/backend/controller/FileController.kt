package by.inbar.backend.controller

import by.inbar.backend.service.model.FileService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/files")
class FileController(
    private val fileService: FileService
) {
    @GetMapping("/{id}")
    fun getFileById(@PathVariable id: Long): ResponseEntity<ByteArray> {
        val file = fileService.getById(id)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, file.mimeType)
            .body(file.data)
    }

    @PostMapping("/upload")
    fun uploadFile(
        @RequestHeader(HttpHeaders.CONTENT_TYPE) contentType: String,
        @RequestBody data: ByteArray,
        @RequestParam(required = false) fileName: String? = null
    ): Long {
        return fileService.save(data, contentType, fileName).id
    }
}
