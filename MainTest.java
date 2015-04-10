import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {

	public static long dateDiff(Date startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - startTime.getTime();
			long day = diff / nd;// 计算差多少天
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void abc() {

		String[] symbols = { "+", "-", "*", "/" };

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();

		for (int i = 0; i < symbols.length; i++) {
			list1.add(symbols[i]);
			list2.add(symbols[i]);
			list3.add(symbols[i]);
			list4.add(symbols[i]);
		}

		List<String> groupList = new ArrayList<String>();

		for (int i1 = 0; i1 < 4; i1++) {

			for (int i2 = 0; i2 < 4; i2++) {

				for (int i3 = 0; i3 < 4; i3++) {

					for (int i4 = 0; i4 < 4; i4++) {
						StringBuffer sb = new StringBuffer();
						sb.append("7");
						sb.append(list1.get(i1));
						sb.append("7");
						sb.append(list1.get(i2));
						sb.append("7");
						sb.append(list1.get(i3));
						sb.append("7");
						sb.append(list1.get(i4));
						sb.append("7");
						groupList.add(sb.toString());
					}
				}
			}
		}
//		System.out.println(groupList.size());

//		List<String> group2List = new ArrayList<String>();
//		for (int i = 0; i < groupList.size(); i++) {
//			String value = groupList.get(i);
//			String temp = "";
//			// (7+7)+7+7+7
//			temp = "(" + value.substring(0, 3) + ")" + value.substring(3, value.length());
//			group2List.add(temp);
//			// (7+7+7)+7+7
//			temp = "(" + value.substring(0, 5) + ")" + value.substring(5, value.length());
//			group2List.add(temp);
//			// (7+7+7+7)+7
//			temp = "(" + value.substring(0, 7) + ")" + value.substring(7, value.length());
//			group2List.add(temp);
//			// 7+(7+7)+7+7
//			temp = value.substring(0, 2) + "(" + value.substring(2, 5) + ")" + value.substring(5, value.length());
//			group2List.add(temp);
//			// 7+(7+7+7)+7
//			temp = value.substring(0, 2) + "(" + value.substring(2, 7) + ")" + value.substring(7, value.length());
//			group2List.add(temp);
//			// 7+(7+7+7+7)
//			temp = value.substring(0, 2) + "(" + value.substring(2, value.length()) + ")";
//			group2List.add(temp);
//			// 7+7+(7+7)+7
//			temp = value.substring(0, 4) + "(" + value.substring(4, 7) + ")" + value.substring(7, value.length());
//			group2List.add(temp);
//			// 7+7+(7+7+7)
//			temp = value.substring(0, 4) + "(" + value.substring(4, value.length()) + ")";
//			group2List.add(temp);
//			// 7+7+7+(7+7)
//			temp = value.substring(0, 6) + "(" + value.substring(6, value.length()) + ")";
//			group2List.add(temp);
//			
//			if (i == 0) {
//				for(String s : group2List) {
//					System.out.println(s);
//				}
//			}
//		}
		
		for (int i = 0; i < groupList.size(); i++) {
			long result = calNoBrackets(groupList.get(i));
			System.out.println(groupList.get(i) + "=" + result);
		}
	}
	
	public static void cal(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String express = list.get(i);
			long result = 0;
			char[] chArr = express.toCharArray();
			
			// 第一步：找出括号
			// 第二步：找出*和/
			// 第三步：计算+和-
			if (express.contains("(")) {
				int leftIndex = express.indexOf("(");
				int rightIndex = express.indexOf(")");
				String step1 = express.substring(leftIndex + 1, rightIndex);
			}
		}
	}
	
	public static long calNoBrackets(String express) {
		String _express = express;
		while (true) {
			
			int sizeMul = _express.indexOf("*");
			int sizeDiv = _express.indexOf("/");
			
			if (sizeMul < 0 && sizeDiv < 0) {
				break;
			}
			
			int size = -1;
			if (sizeMul > 0 && sizeDiv < 0) {
				size = sizeMul;
			} else if (sizeMul < 0 && sizeDiv > 0) {
				size = sizeDiv;
			} else {
				size = sizeMul > sizeDiv ? sizeDiv : sizeMul;
			}
			
			String eStart = _express.substring(0, size - 1);
			String eWaitCal = _express.substring(size - 1, size + 2);
			_express = eStart + calMultOrDiv(eWaitCal)
					+ _express.substring(size + 2, _express.length());
		}
		
		return calAddOrSub(_express);
	}
	
	public static long calMultOrDiv1(String express) {
		char[] chArr = express.toCharArray();
		char c = chArr[0];
		String s = String.valueOf(c);
		long resultL = Long.valueOf(s);
		for (int i = 0; i < chArr.length; i++) {
			char ch = chArr[i];
			String a = String.valueOf(ch);
			// 数字项
			if (i % 2 == 0 && i > 0) {
				long endL = Long.valueOf(a);
				// 符号项
				if (chArr[i - 1] == '*') {
					resultL = resultL * endL;
				} else {
					resultL = resultL / endL;
				}
			}
		}
		return resultL;
	}
	
	public static long calMultOrDiv(String express) {
		char[] chArr = express.toCharArray();
		char symbol = '*';
		long resultL = 1;
		String end = "0";
		for (int i = 0; i < chArr.length; i++) {
			char ch = chArr[i];
			// 是符号项，计算符号前的表达式，然后把新的符号复制给symbol，end清空
			if (ch == '*' || ch == '/') {
				if (symbol == '*') {
					resultL *= Long.valueOf(end);
				} else {
					resultL /= Long.valueOf(end);
				}
				symbol = ch;
				end = "0";
			} else if (i == chArr.length - 1) { // 不是符号，但是已经到最后的一个字符了
				end += String.valueOf(ch);
				if (symbol == '*') {
					resultL *= Long.valueOf(end);
				} else {
					resultL /= Long.valueOf(end);
				}
			} else { // 不是符号项，也不是最后一项，把数字字符串拼接起来
				end += String.valueOf(ch);
			}
		}
		return resultL;
	}
	
	public static long calAddOrSub(String express) {
		char[] chArr = express.toCharArray();
		char symbol = '+';
		long resultL = 0;
		String end = "0";
		for (int i = 0; i < chArr.length; i++) {
			char ch = chArr[i];
			// 是符号项，计算符号前的表达式，然后把新的符号复制给symbol，end清空
			if (ch == '+' || ch == '-') {
				if (symbol == '+') {
					resultL += Long.valueOf(end);
				} else {
					resultL -= Long.valueOf(end);
				}
				symbol = ch;
				end = "0";
			} else if (i == chArr.length - 1) { // 不是符号，但是已经到最后的一个字符了
				end += String.valueOf(ch);
				if (symbol == '+') {
					resultL += Long.valueOf(end);
				} else {
					resultL -= Long.valueOf(end);
				}
			} else { // 不是符号项，也不是最后一项，把数字字符串拼接起来
				end += String.valueOf(ch);
			}
		}
		return resultL;
	}
	
	public static void main(String[] args) {
		
//		String express = "7*7*7+7+7*7-7";
////		
//		System.out.println(10*120*3*77*8*9*7);
//		System.out.println(calMultOrDiv1("10*120*3*77*8*9*7"));
		abc();
		

	}
}
