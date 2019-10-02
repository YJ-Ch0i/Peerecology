package Common;

import java.io.File;

/**
 * 공통 메소드 관리 클래스
 * @author yeong
 *
 */
public class CommonUtil {

	/**
	 * String 변수 null 체크
	 * @param str
	 * @return true: 빈 값일 경우, false: 빈 값이 아닐 경우
	 */
	public static boolean isNullString(String str) {
		if (null == str || Constant.BLANK.equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * str 변수 not null체크
	 * @param str
	 * @return
	 */
	public static boolean isNotNullString(String str) {
		return !isNullString(str);
	}
	
	/**
	 * String 변수 2개가 일치 혹은 포함 관계인지 파악
	 * @param str1
	 * @param str2
	 * @return true: 두 변수가 일치 혹은 포함하는 경우, false: 두 변수가 일치 혹은 포함하지 않는 경우
	 */
	public static boolean isEqualOrContainsString(String str1, String str2) {
		if(isNotNullString(str1) && isNotNullString(str2)) {
			if(str1.contains(str2) || str1.equals(str2) || str2.contains(str1) || str2.equals(str1)) {
				return true;
			}
			else return false;
		}
		return false;
	}
	
	public static boolean isNotEqualOrContainsString(String str1, String str2) {
		return !isEqualOrContainsString(str1, str2);
	}
	
	/**
	 * str 변수 2개가 같은지 여부
	 * @param str1
	 * @param str2
	 * @return true : 두 변수가 일치함, false : 두 변수가 다름
	 */
	public static boolean isEqualString(String str1, String str2) {
		if(isNotNullString(str1) && isNotNullString(str2)) {
			if(str1.equals(str2) || str2.equals(str1) || str1 == str2) {
				return true;
			}
			else return false;
		}
		return false;
	}
	
	public static boolean isNotEqualString(String str1, String str2) {
		return !isEqualString(str1, str2);
	}
	
	/**
	 * 문자열 -> 정수형 형변환
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		if (isNullString(str)) {
			return 0;
		}
		return Integer.parseInt(str.trim());
	}
	
	/**
	 * 정수형 0여부
	 * @param num
	 * @return
	 */
	public static boolean isZeroInt(int num) {
		if(num == Constant.ZERO) {
			return false;
		}
		return true;
	}
	
	/**
	 * 설정한 경로대로 폴더를 생성하는 메소드
	 * @param path
	 * @return
	 */
	public static void createFolder(String path) {
		File folder = new File(path);
		
		if(!folder.exists()) {
			folder.mkdirs();	        
		}
	}
}
