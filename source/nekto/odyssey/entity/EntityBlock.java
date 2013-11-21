package nekto.odyssey.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBlock extends Entity
{
	public int blockId;
	public int meta;

	public EntityBlock(World par1World)
	{
		super(par1World);

		setSize(1.0F, 1.0F);
		entityCollisionReduction = 1;
	}

	public EntityBlock(World par1World, int x, int y, int z, int id, int meta)
	{
		super(par1World);

		setSize(1.0F, 1.0F);
		entityCollisionReduction = 1;
		this.yOffset = (this.height / 2);
		this.setLocationAndAngles(x + 0.5, y, z + 0.5, 0, 0);
		this.blockId = id;
		this.meta = meta;
	}
	
	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		if(this.isCollided)
		{
			pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
		}
		
		moveEntity(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
	protected void entityInit()
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer)
	{
		this.rejoinWorld();
		return true;
	}

	public void rejoinWorld()
	{
		worldObj.setBlock((int) Math.round(posX - 0.5),
				(int) Math.round(posY - 0.5), (int) Math.round(posZ - 0.5),
				this.blockId, this.meta, 2);

		this.setDead();
	}

	public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return par1Entity.boundingBox;
    }
	
	public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }
}
