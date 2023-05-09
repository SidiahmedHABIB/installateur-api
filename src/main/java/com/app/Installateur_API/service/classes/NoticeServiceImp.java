package com.app.Installateur_API.service.classes;
import com.app.Installateur_API.entity.Notice;
import com.app.Installateur_API.entity.page.PageNotice;
import com.app.Installateur_API.repository.NoticeRepository;
import com.app.Installateur_API.service.interfaces.INoticeService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class NoticeServiceImp implements INoticeService {
    @Autowired
    NoticeRepository noticeRepository;
    @Override
    public String uploadNotice(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Notice notice = noticeRepository.save(Notice.builder()
                    .name(UUID.randomUUID().toString()+".pdf")
                    .type(file.getContentType())
                    .noticeData(file.getBytes())
                    .creatAt(new Date())
                    .updateAt(new Date())
                    .build());
            return "file uploaded successfully : " + notice.getName();

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public PageNotice getPageNotice(int page, int size) {
        PageNotice p= new PageNotice();
        Page<Notice> interventionPage = noticeRepository.findAll(PageRequest.of(page, size));
        p.setNotices(interventionPage.getContent());
        p.setTotalPages(interventionPage.getTotalPages());
        return p;
    }

    @Override
    public Notice downloadNotice(String fileName) {
        return noticeRepository.findByName(fileName).get();
    }
}
