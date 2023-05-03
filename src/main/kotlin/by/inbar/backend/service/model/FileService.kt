package by.inbar.backend.service.model

import by.inbar.backend.exception.NotFoundException
import by.inbar.backend.model.File
import by.inbar.backend.repository.FileRepository
import org.springframework.stereotype.Service

@Service
class FileService(
    private val fileRepository: FileRepository
) {
    fun save(data: ByteArray, mimeType: String, fileName: String? = null): File {
        val file = File(data, mimeType, fileName)
        return fileRepository.save(file)
    }

    fun getById(id: Long): File {
        return fileRepository.findById(id)
            .orElseThrow { throw NotFoundException("File with id=$id not found") }
    }

    fun delete(id: Long) {
        fileRepository.deleteById(id)
    }
}
