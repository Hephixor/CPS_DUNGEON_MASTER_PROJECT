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
	
	public void checkInvariants() {
		
	}
	
	/* Observators */
	@Override
	public int getHP() {
		//pre
		
		//checkInt
		checkInvariants();
		
		return super.getHP();
		

	}
	
	/* Constructors */
	/**
	 * pre init(E,x,y,D,h) requires h > 0
	 */
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		//pre
		if(! (hp > 0) ) {
			throw new PreconditionError("HP must be at least 1.");
		}
		
		checkInvariants();
		
		super.init(env, x, y, d, hp);
		
		checkInvariants();
		
		//post
		//Hp(init(E,x,y,D,h)) = h
		if(super.getHP()!=hp) {
			throw new PostconditionError("Erro while initializing HP");
		}
		
	}
	
	/* Operators */
	public EntityService step() {
		//pre
		
		checkInvariants();
		
		super.step();
	
		checkInvariants();
		
		//post
		
		return this;
	}

}
