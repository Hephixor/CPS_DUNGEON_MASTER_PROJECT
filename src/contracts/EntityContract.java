package contracts;

import decorators.EntityDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EntityService;
import services.EnvironmentService;
import utils.Dir;

public class EntityContract extends EntityDecorator{

	public EntityContract(EntityService delegate) {
		super(delegate);
	}
	
	
	/*========== invariants ==========*/
	
	public void checkInvariants() {
		//dependances
		MobContract mob = new MobContract(this);
		mob.checkInvariant();
	}
	
	
	/*========== preconditions ==========*/
	
	public void preInit(EnvironmentService e, int x, int y, int hp) {
		//dependances
		MobContract mob = new MobContract(this);
		mob.preInit(e, x, y);
		
		//pre
		if(! (hp > 0) )
			throw new PreconditionError("HP must be at least 1.");
	}
	
	public void preTakeHit() {
		if(getHP()<=0)
			throw new PreconditionError("Error cannot kill the dead");
	}
	
	/*========== postconditions ==========*/
	
	public void postInit(EnvironmentService e, int x, int y, Dir d, int hp) {
		//dependances
		MobContract mob = new MobContract(this);
		mob.postInit(e, x, y, d);
		
		//post
		if(getHP()!=hp)
			throw new PostconditionError("Erro while initializing HP");
	}
	
	public void postTakeHit(int hp_atpre) {
		if(getHP()!=hp_atpre-1)
			throw new PostconditionError("Error hit didn't inflict 1 damage");
	}
	
	/*========== observateurs ==========*/
	
	/*========== constructeurs ==========*/
	
	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		//pre
		preInit(env, x, y, hp);
		
		//inv pre
		checkInvariants();
		
		//run
		super.init(env, x, y, d, hp);
		
		//inv post
		checkInvariants();
		
		//post
		postInit(env, x, y, d, hp);
	}
	
	
	/*========== operateurs ==========*/
	
	
	/* Operators */
	
	@Override
	public void step() {
		//pre
		
		//inv pre
		checkInvariants();
		
		//run
		super.step();
	
		//inv post
		checkInvariants();
		
		//post
	}

	@Override
	public void hit() {
		//pre
		
		//inv pre
		checkInvariants();
		
		//run
		super.hit();
		
		//inv post
		checkInvariants();
		
		//post	
	}
	
	@Override
	public void takeHit() {
		//pre
		preTakeHit();

		//inv pre
		checkInvariants();
		
		//capture
		int hp_atpre = getHP();
		//run
		super.takeHit();

		//inv post
		checkInvariants();

		//post 
		postTakeHit(hp_atpre);
		
	}
}
