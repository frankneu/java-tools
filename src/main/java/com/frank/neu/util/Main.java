package com.frank.neu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * ACM代码
 * @author frank
 *
 */
public class Main {
	
	/**
	 * 网易
	 * https://www.nowcoder.com/test/question/0147cbd790724bc9ae0b779aaf7c5b50?pid=2811407&tid=6103989
	 * [编程题] 回文序列
		如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
		{1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列, 
		{1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
		现在给出一个数字序列，允许使用一种转换操作：
		选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数之前的位置(只插入一个和)。
		现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。
	 * 解题报告：相当于从两侧向内，对数组进行遍历，如果两侧数字相同，则start和end则相应向内移动一位
	 *			1. 合并数字的时候，头尾都是在begin和end的指定位置插入的，注意index
	 *			2. 自行编写测试用例，降低代码提交次数
	 * @param list
	 * @return
	 */
	public static int countReverse(List<Integer> list){
        int counts = 0;
        int begin = 0;
        int end = list.size();
        while(begin<end-1){
        	int header = list.get(begin);
        	int tail = list.get(end - 1);
            if(header == tail){
                begin ++;
                end --;
            }else{
                if(header > tail){
                    int a = list.get(end - 1);
                    int b = list.get(end - 2);
                    list.remove(end-2);
                    list.remove(end-2);
                    list.add(end - 2, a+b);
                }else{
                    int a = list.get(begin);
                    int b = list.get(begin + 1);
                    list.remove(begin);
                    list.remove(begin);
                    list.add(begin, (a+b));
                }
                end --;
                counts ++;
            }
        }
        return counts;
    }
	/**
	 * 网易
	 * https://www.nowcoder.com/question/next?pid=2811407&qid=46572&tid=6103989
	 * 小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
		例如：半径的平方如果为25
		优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。 
	 * 解题报告：遍历0-r，对每个r^2-i^2开平方，结果判断是否是整数
	 * @param r
	 * @return
	 */
	public static int gracePoint(double rsquare){
		double r = Math.sqrt(rsquare);
		int count = 0;
		for(int i=0; i<=r; ++i){
			double remain = rsquare - i*i;
			int j = (int)Math.sqrt(remain);
			if(j*j == remain){
				if(i ==0 || i == r){
					count += 2;
				}else{
					count += 4;
				}
			}
		}
		return count;
	}
	
	
	/**
	 * 网易
	 * https://www.nowcoder.com/question/next?pid=2811407&qid=46573&tid=6103989
	 * 小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
		这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
		例如：
		N = 4，M = 24：
		4->6->8->12->18->24
		于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板 
	 * 解题报告：
	 * @param startr
	 * @param end
	 * @return
	 */
	public static int stoneRoad(int start, int end){
		return 0;
	}
	
	/**
	 * 网易
	 * https://www.nowcoder.com/question/next?pid=2811407&qid=46575&tid=6103989
	 * 对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
		如果 X = 123，则rev(X) = 321;
		如果 X = 100，则rev(X) = 1.
		现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？ 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int reverseAdd(int a, int b){
		int c = reverseNumber(a) + reverseNumber(b);
		return reverseNumber(c);
	}
	private static int reverseNumber(int a){
		int b = 0;
		while(a>0){
			int remain = a%10;
			b *= 10;
			b += remain;
			a /= 10;
		}
		return b;
	}
	
	/**
	 * 网易
	 * https://www.nowcoder.com/question/next?pid=2811407&qid=46577&tid=6103989
	 * 小易去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装(包装不可拆分)。 可是小易现在只想购买恰好n个苹果，小易想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小易将不会购买。
	 * 解题报告：近似贪心算法，nuber/8，然后回溯替换成6个的袋子，如果回溯到最后也不能恰好，则返回-1
	 * 			1. 回溯，将剩余的+8，看是否能整除6
	 * 			2. 如果加到number自身时，仍然无法整除6，返回-1
	 * @param number
	 * @return
	 */
	public static int buyApple(int number){
		int count_8 = number/8;
		int count_6 = 0;
		while(count_8 >= 0){
			count_6 = (number - count_8 * 8)/6;
			int remain = number - count_8 * 8 -count_6 * 6;
			if(remain == 0){
				return count_8 + count_6;
			}
			count_8 --;
		}
		return -1;
	}
	
	/**
	 * 网易
	 * https://www.nowcoder.com/question/next?pid=2811407&qid=46578&tid=6103989
	 * A,B,C三个人是好朋友,每个人手里都有一些糖果,我们不知道他们每个人手上具体有多少个糖果,但是我们知道以下的信息：
		A - B, B - C, A + B, B + C. 这四个数值.每个字母代表每个人所拥有的糖果数.
		现在需要通过这四个数值计算出每个人手里有多少个糖果,即A,B,C。这里保证最多只有一组整数A,B,C满足所有题设条件。 
		解题报告：多元方程组求解
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static String countCandy(int x, int y, int z, int m){
		if((y+m)%2 != 0){
			return "No";
		}
		if((m-y)%2 != 0){
			return "No";
		}
		int a = x + (y + m)/2;
		int b = (y+m)/2;
		int c = (m-y)/2;
		if((a + b) == z){
			StringBuilder sb = new StringBuilder();
			sb.append(a);
			sb.append(" ");
			sb.append(b);
			sb.append(" ");
			sb.append(c);
			return sb.toString();
		}else{
			return "No";
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {// 注意while处理多个case
			int x = in.nextInt();
			int y = in.nextInt();
			int z = in.nextInt();
			int m = in.nextInt();
			//调用算法
			System.out.println(countCandy(x, y, z, m));
		}
		in.close();
	}
}
