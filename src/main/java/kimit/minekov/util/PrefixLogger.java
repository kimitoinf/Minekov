package kimit.minekov.util;

import java.util.logging.Logger;

public class PrefixLogger
{
	private final String PREFIX = "[Minekov] ";
	private Logger LOGGER;


	public PrefixLogger(Logger logger)
	{
		this.LOGGER = logger;
	}

	public void Log(String message)
	{
		this.LOGGER.info(PREFIX + message);
	}
}
