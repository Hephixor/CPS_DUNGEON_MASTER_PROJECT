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
		return super.getHP();
	}
	
	/* Constructors */
	
	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		//pre
		if(! (hp > 0) ) {
			throw new PreconditionError("HP must be at least 1.");
		}
		
		//inv pre
		checkInvariants();
		
		//run
		super.init(env, x, y, d, hp);
		
		//inv post
		checkInvariants();
		
		//post
		//Hp(init(E,x,y,D,h)) = h
		if(super.getHP()!=hp) {
			throw new PostconditionError("Erro while initializing HP");
		}
		
	}
	
	/* Operators */
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

}
