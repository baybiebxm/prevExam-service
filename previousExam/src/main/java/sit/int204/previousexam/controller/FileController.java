package sit.int204.previousexam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.previousexam.services.FileService;

@RestController
@RequestMapping("api/64130500026/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = fileService.loadFileAsResource(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }

    @PostMapping("")
    public String fileUpload(@RequestParam("file")MultipartFile file){
        fileService.store(file);
        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }
}
