package cafe.jjdev.web.service;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.web.FileRequest;
@Service
public class FileuploadService {
	public int fileupload(FileRequest fileRequest) {
		MultipartFile file = fileRequest.getFile();
		String fileName = file.getOriginalFilename();
		int pos = fileName.lastIndexOf("."); //���Ͽ��� ���� ��ġ�� ã���ּ���
		String ext = fileName.substring(pos+1); //.���� ��ġ�� Ȯ���ڸ� ����ϰ� �Ѵ�.
		System.out.println("ext:"+ext);
		
		UUID uuid = UUID.randomUUID();
		String name = uuid.toString();
		name = name.replaceAll("-", "");
		System.out.println(name+"."+ext); //���� �̸� . Ȯ���ڸ�
		
		File destFile = new File("c:/upload/"+name+"."+ext);
		try {
			file.transferTo(destFile);
			FileVo fileVo = new FileVo();
			fileVo.setFileTitle(fileRequest.getFileTitle());
			fileVo.setFilePath("c:/upload/"+name+"."+ext);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*FileVo fileVo = new FileVo();
		fileVo.setFileTitle(fileRequest.getFileTitle());
		fileVo.setFilePath("c:/upload/"+name+"."+ext);*/
		//FileDao.insertFile(fileVo);
		//1. ������ ������ ����
		//2. FileReuqest type -> fileVo
		//3. FileDao.insertFile() ȣ��
		return 0;
	}
}
