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
		if(getHP()!=hp) {
			throw new PostconditionError("Erro while initializing HP");
		}	
	}
	
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
		//verifier que la cible à perdu des points de vie / porte ouverte/fermée ?
		
	}
	
	@Override
	public void takeHit() {
		//pre
		// takeHit(E) implies HP(E)>0
		if(getHP()<=0){
			throw new PreconditionError("Error cannot kill the dead");
		}

		//inv pre
		checkInvariants();
		
		//capture
		int hp_atpre = getHP();
		//run
		super.takeHit();

		//inv post
		checkInvariants();

		//post 
		if(getHP()!=hp_atpre-1){
			throw new PostconditionError("Error hit didn't inflict 1 damage");
		}
		
	}
}
