package contracts;

import decorators.EngineDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EngineService;
import services.EntityService;
import services.EnvironmentService;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		EntityService[] ents = this.entities();
		
		/**
		 * forall i in [0;size(Entities(E))-1], Entity::Envi(getEntity(E,i))=Envi(E)
		 */
		//a revoir
		for(int i = 0 ; i < ents.length ; i++) {
			if(ents[i]!=getEntity(i)) {
				throw new InvariantError("Entities in env mismatch");
			}
		}
		
		/**
		 * forall i in [0;size(Entities(E))-1], Entity::Col(getEntity(E,i))=x
		 *	and Entity::Row(getEntity(E,i))=y
		 *  implies Environment::CellContent(Envi(E),x,y) = getEntity(E,i)
		 */
		//a revoir
		for(int i = 0 ; i < ents.length ; i++) {
			int xent = getEntity(i).getCol();
			int yent = getEntity(i).getRow();
			
			//envi().CellContent(xent,yent)!=ents[i]
			//remplacer condition
			if(xent==0||yent==0) {
				throw new InvariantError("Entities in env mismatch");
			}
		}
		
		
	}
	
	@Override
	public EnvironmentService envi() {
		//pre
		
		checkInvariants();
		
		return super.envi();
			
		
		
	}
	
	@Override
	public EntityService[] entities() {
		//pre
		//checkInv
		checkInvariants();
		
		return super.entities();
	}

	@Override
	public EntityService getEntity(int idx) {
		//pre
		//checkInv
		checkInvariants();
		return super.getEntity(idx);
	}


	@Override
	public void init(EnvironmentService env) {
		super.init(env);
	}
	
	
	/**
	 * pre removeEntity(E,i) requires 0 ≤ i < size(Entities(E))
	 */
	@Override
	public EngineService removeEntity(int idx) {
		//pre
		//pourquoi 0 ≤ i < size et non 0 ≤ i ≤ size ?
		if(idx<0 || idx >= entities().length) {
			throw new PreconditionError("Invalide entity index");
		}
		
		//check invariants
		checkInvariants();
		
		//capture
		int size_atpre = entities().length;
		EntityService[] ents_atpre_bidx= new EntityService[entities().length -1];
		EntityService[] ents_atpre_aidx= new EntityService[entities().length -1];
		
		for(int i = 0 ; i < idx - 1; i++) {
			ents_atpre_bidx[i]=getEntity(i);
		}
		
		for(int i = idx ; i < entities().length-2; i++) {
			ents_atpre_aidx[i]=getEntity(i);
		}
		
		super.removeEntity(idx);
		
		checkInvariants();
		
		//post
		
		//size(Entities(removeEntity(E,i))) = size(Entities(E)) - 1
		if(entities().length!=size_atpre-1) {
			throw new PostconditionError("Error removing entity");
		}
		
		//forall k in [0,i-1], getEntity(removeEntity(E,i),k)) = getEntity(E,k)
		for(int k = 0 ; k < idx -1 ; k++) {
			if((getEntity(k))!=ents_atpre_bidx[k]) {
				throw new PostconditionError("Error removing entity removed another");
			}
		}
		
		//TODO moyen sûr
		//forall k in [i,size(Entities(E))-2], getEntity(removeEntity(E,i),k)) = getEntity(E,k+1)
		for(int k = idx ; k < entities().length-2 ; k++) {
			if((getEntity(k+1))!=ents_atpre_aidx[k]) {
				throw new PostconditionError("Error removing entity removed another");
			}
		}
		
		return this;
	}

	@Override
	public EngineService addEntity(EntityService ent) {
		//pre
		
		//checkInv
		checkInvariants();
		
		//capture
		int size_atpre = entities().length;
		EntityService[] ents_atpre_aidx= new EntityService[entities().length -1];
		
		for(int i = 0 ; i < entities().length - 1; i++) {
			ents_atpre_aidx[i]=getEntity(i);
		}
		
		super.addEntity(ent);
		
		//checkInv
		checkInvariants();
		
		//post
		//size(Entities(addEntity(E,e))) = size(Entities(E)) + 1
		if(entities().length!=size_atpre + 1) {
			throw new PostconditionError("Error adding new entity");
		}
		
		//forall k in [0,size(Entities(E))-1], getEntity(addEntity(E,e),k)) = getEntity(E,k)
		for(int k = 0 ; k < entities().length-1 ; k++) {
			if((getEntity(k))!=ents_atpre_aidx[k]) {
				throw new PostconditionError("Error adding entity modified another");
			}
		}
		
		//getEntity(addEntity(E,e),size(Entities(E))) = e
		if(!(getEntity(entities().length)==ent)){
			throw new PostconditionError("Entity not added at the end.");
		}
		
		return this;
	}

	/**
	 * pre step() requires forall i in [0;size(Entities(E))-1], Entity::Hp(getEntity(E,i))>0
	 */
	@Override
	public EngineService step() {
		//pre
		for(int i = 0 ; i < entities().length -1 ; i++) {
			if(!(getEntity(i).getHP()>0)) {
				throw new PreconditionError("I see dead people");
			}
		}
		
		checkInvariants();
		
		super.step();
		
		checkInvariants();
		
		//post
		
		return this;
	}
	

}