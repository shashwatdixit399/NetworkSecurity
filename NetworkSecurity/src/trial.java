
public class trial {

	public static void main(String[] args) {
		int n=1;
		for(int i=1;i<=23;i++)
		{
			n*=11;
			n=n%187;
		}
		System.out.println(n%187);
	}

}
