package kimit.minekov.Island;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class EmptyChunk extends ChunkGenerator
{
	@Override
	public boolean shouldGenerateCaves()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateCaves(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}

	@Override
	public boolean shouldGenerateDecorations()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateDecorations(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}

	@Override
	public boolean shouldGenerateMobs()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateMobs(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}

	@Override
	public boolean shouldGenerateNoise()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}

	@Override
	public boolean shouldGenerateStructures()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateStructures(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}

	@Override
	public boolean shouldGenerateSurface()
	{
		return false;
	}

	@Override
	public boolean shouldGenerateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ)
	{
		return false;
	}
}
