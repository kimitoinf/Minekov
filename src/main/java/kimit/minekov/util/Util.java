package kimit.minekov.util;

public class Util
{
	public static boolean IsNumberic(String string)
	{
		try
		{
			Long.parseLong(string);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
