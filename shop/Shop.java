package lian;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
/*简易售货机
流程：
【投币】【显示货物清单】【选择一个商品编号购买】【提示出货】【找钱】 

功能要求：
1. 使用mysql数据库，将所有货物存储在数据库中（货物应至少具有编号、名称、数量、价格等基本信息，可自行增加其他属性以完善程序）。
2. 要有友好的客户提示，例如：请输入购买商品的编号。
3. 清单要求包含每种商品的剩余数量。
4. 出货后，可以选择【找钱】，也可以选择【继续购买】，而不直接找钱
*/
public class Shop {
	public static void main(String[] args) {
		System.out.println("请投币");
		Scanner in = new Scanner(System.in);
		int which = in.nextInt();
		
		//连接数据库
		Shopjdbc buy = Shopjdbc.dao();
		List<Map<String,String>> list = buy.find("select * from shop ");
		for(Map<String,String> list2:list){
			System.out.println(list2);
		}
		System.out.println("请选择购买物品");
		System.out.println("1、饮料\n");
		String what = in.next();
		//将数据库中”饮料“的have属性减少”1“
		//需要动态写入数量进入数据库
		//抑或是最在方法层面解决
		buy.update("update shop have=? where name", params)
	}
}
