package kimit.minekov.Command;

import org.bukkit.command.CommandSender;

public abstract class Executor
{
	protected static final String ARGUMENTS_ERROR = "Invalid arguments.";
	protected static final String NOT_PLAYER_ERROR = "Only player can execute this command.";
	protected static final String INRAID_ERROR = "레이드 중에는 이 명령어를 사용할 수 없습니다.";

	abstract public void Run(CommandSender sender, String[] args);
}