package com.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Post;
import com.spring.repository.AttachmentRepository;

@Service
@Transactional(readOnly = true)
public class AttachmentService {
  private final AttachmentRepository attachmentRepository;
  private final Path uploadPath;

  public AttachmentService(AttachmentRepository attachmentRepository,
    @Value("${app.upload.dir}") String uploadDir)
  {
    this.attachmentRepository = attachmentRepository;
    this.uploadPath = Paths.get(uploadDir).toAbsolutePath();
  }

  @Transactional
  public void saveFiles(MultipartFile[] files, Post post) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveFiles'");
  }

}
