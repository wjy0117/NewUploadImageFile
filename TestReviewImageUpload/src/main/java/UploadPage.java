
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadPage
 */
@WebServlet("/UploadPage")
public class UploadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// 한글처리
        request.setCharacterEncoding("utf-8");
        
        // 컨텍스트 페스 경로가져오기
        String context = request.getContextPath();
        
        //String name_text = multi.getParameter("name_text");
        
		//실제로 파일이 저장될 절대경로 가져오기 (root 폴더에 UserImageFile이 들어가는 경로)
	    //String path = request.getRealPath("/UserImageFile");
	    
		//String ImgCount = (String) request.getAttribute("imgstr");
		//웹페이지에 출력
	    //System.out.println("절대 경로: " + path);
	    
	    /*int size = 1024 * 1024 * 10; // 파일 사이즈 설정 : 10M
	    String fileName = "";    // 업로드한 파일 이름(올리고 가져오기 위한)
	    String originalFileName = "";    //  서버에 중복된 파일 이름이 존재할 경우 처리하기 위해
	    String filename_str = "";*/
	    String img_str = "";
	    // cos.jar라이브러리 클래스를 가지고 실제 파일을 업로드하는 과정
	    try{
	    	String path = "C:/Apache24/htdocs/ImageTest/storeImgSub";
	    	//위에서만든 절대경로를 가져와 파일 경로로 지정.
	    	/*File f = new File(path);
	    	//파일이 있는지 체크
	    	if(!f.exists()){
	    		//파일폴더 생성.
	            f.mkdir();
	        }*/
	    	
	        // DefaultFileRenamePolicy 처리는 중복된 이름이 존재할 경우 처리할 때
	        // request(정보가져와서 이미지 저장), 파일저장경로, 용량, 인코딩타입, 중복파일명에 대한 정책
	        //MultipartRequest를 사용하기 위한 외부 라이브러리 cos.jar
	    	//request타입을 바꾸는 라이브러리
	    	
	    	//form에서 입력한 내용 가져오기
	    	MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 10, "EUC-KR", new DefaultFileRenamePolicy());
	    	
	    	//input file로 입력한 이미지명 가져오기.
	    	//multi에서는 여러개의 파일명을 꺼낼 수가 없어서 form을 통해 해당내용을 전달받는다.
	    	String nt = multi.getParameter("name_text");
	    	
	        /*
	        // 전송한 전체 파일이름들을 가져온다.
	        
	        Enumeration<String> files = multi.getFileNames();
	        
	        while(files.hasMoreElements()){
	        	filename_str = (String)files.nextElement();
	            //input file name 정보
	            System.out.println("여기: "+filename_str);
	            
	            //파일명이 중복일 때 수정된 파일명.                       
	            //파일명 중복이 발생했을 때 정책에 의해 뒤에 1,2,3 처럼 숫자가 붙어 고유 파일명을 생성한다.
	            // 이때 생성된 이름을 FilesystemName이라고 하여 그 이름 정보를 가져온다. (중복 처리)
	            fileName = multi.getFilesystemName(filename_str);
	        }
	        */
	    	
	    	//db에 입력하기위한 img_str에 입력된 모든 경로를 db입력방식의 문자열 방식으로 변경함. 
	        String imgstr[] = nt.split(",");
	        for(int i=1; i<imgstr.length; i++) {
	        	
	        	File fileimg = new File("/ImageTest/storeImgSub/"+imgstr[i]);
	        	File fileimgname = new File("/ImageTest/storeImgSub/"+i+".jpg");
	        	
	        	fileimg.renameTo(fileimgname);
	        	if(i==1) {
	        		img_str = '"'+fileimgname.toString()+'"';
	        	}else {
	        		img_str = img_str+","+'"'+fileimgname.toString()+'"';
	        	}
	        }
	        System.out.println("전체 이미지 경로: "+img_str);
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    response.sendRedirect(context + "/Uploadeform.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
