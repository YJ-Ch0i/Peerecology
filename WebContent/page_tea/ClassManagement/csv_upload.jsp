<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ include file="/pageInclude/CheckTeacherLogin.jsp"%>

<%
	request.setCharacterEncoding("UTF-8");
	try {
		//String tsUploadPath = request.getRealPath("upload");
		String tsUploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		int tnLimitSize = 1024 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, tsUploadPath, tnLimitSize, "UTF-8");

		Enumeration enFiles = multi.getFileNames();

		while (enFiles.hasMoreElements()) {
			String tsObjectName = (String) enFiles.nextElement();

			File file = multi.getFile(tsObjectName);

			if (file != null && file.length() > 0) {
				String tsFileNameSrv = multi.getFilesystemName(tsObjectName);
				String tsFileExt = tsFileNameSrv.substring(tsFileNameSrv.lastIndexOf(".") + 1,
						tsFileNameSrv.length());
				String tsFileSize = String.valueOf((int) file.length());

				FileInputStream toFileInputStream = new FileInputStream(file);
				InputStreamReader toInputStreamReader = new InputStreamReader(toFileInputStream, "UTF-8");
				BufferedReader toBufferedReader = new BufferedReader(toInputStreamReader);

				try {
					session.removeAttribute("file_name");
					session.setAttribute("file_name", tsFileNameSrv);
					String tsRecordLine = "";
					String[] taRecord;
					int tnRecordIndex = 0;
					while ((tsRecordLine = toBufferedReader.readLine()) != null) {
						tnRecordIndex++;
						System.out.println();
						taRecord = tsRecordLine.split(",", 1);

						for (int i = 0; i < 1; i++) {
							System.out.print(taRecord[i] + " ");

						}
					}
					out.println("<script language='javascript'>");
					out.println("alert('업로드가 완료되었습니다.');");
					out.println("href=history.back()");
					out.println("</script>");

				}

				catch (Exception e) {
					out.println("<script language='javascript'>");
					out.println("alert('파일 포멧을 확인해주세요.');");
					out.println("href=history.back()");
					out.println("</script>");
					e.printStackTrace();
					return;
				}
			}

			else {
				out.println("<script language='javascript'>");
				out.println("alert('존재하지 않는 파일입니다.');");
				out.println("href=history.back()");
				out.println("</script>");
				return;
			}
		}
	}

	catch (Exception e) {
		out.println("<script language='javascript'>");
		out.println("alert('업로드중에 에러가 발생하였습니다.');");
		out.println("href=history.back()");
		out.println("</script>");
		e.printStackTrace();
		return;
	}
%>
