package Common;

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
}
