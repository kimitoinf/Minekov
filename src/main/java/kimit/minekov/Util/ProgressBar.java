package kimit.minekov.Util;

public class ProgressBar
{
	private final char PROGRESSED;
	private final char NOT_PROGRESSED;

	public ProgressBar(char progressed, char notProgressed)
	{
		PROGRESSED = progressed;
		NOT_PROGRESSED = notProgressed;
	}

	public String GetString(double progress)
	{
		int floored = (int)(progress * 10);
		return String.valueOf(PROGRESSED).repeat(floored).concat(String.valueOf(NOT_PROGRESSED).repeat(10 - floored));
	}
}
