package contracts;

import decorators.EntityDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EntityService;
import services.EnvironmentService;
import services.MobService;
import utils.Cell;
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

	public void  forward() {
		MobContract mob = new MobContract(this);
		
		//pre
		
		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.forward();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postForward(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
				
	}

	@Override
	public void backward() {
		MobContract mob = new MobContract(this);
		
		//pre

		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.backward();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postBackward(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}

	@Override
	public void strafeL() {
		MobContract mob = new MobContract(this);
		
		//pre

		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.strafeL();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postStrafeL(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}

	@Override
	public void strafeR() {
		MobContract mob = new MobContract(this);
		
		//pre

		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.strafeR();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postStrafeR(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}
	
	@Override
	public void turnL() {
		MobContract mob = new MobContract(this);
		
		//pre
		
		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		
		//run
		super.turnL();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postTurnL(face_atpre);
	}

	@Override
	public void turnR() {
		MobContract mob = new MobContract(this);
		
		//pre

		//inv pre
		checkInvariants();
		
		//capture
		Dir face_atpre = getFace();
		
		//run
		super.turnR();
		
		//inv post
		checkInvariants();
		
		//post
		mob.postTurnR(face_atpre);
	}


}
