package contracts;

import java.util.ArrayList;
import java.util.List;

import services.EngineService;
import services.EntityService;
import services.EnvironmentService;
import decorators.EngineDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;

public class EngineContract extends EngineDecorator{

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {

		
		/**
		 * forall i in [0;size(Entities(E))-1], Entity::Envi(getEntity(E,i))=Envi(E)
		 */
		
		List<EntityService> ents = getEntities();
		for(int i = 0 ; i < getEntities().size() ; i++) {
			if(ents.get(i).getEnv()!=envi()) {
				throw new InvariantError("Entities in env mismatch");
			}
		}
		
		/**
		 * forall i in [0;size(Entities(E))-1], Entity::Col(getEntity(E,i))=x
		 *	and Entity::Row(getEntity(E,i))=y
		 *  implies Environment::CellContent(Envi(E),x,y) = getEntity(E,i)
		 */
		
		for(int i = 0 ; i < ents.size() ; i++) {
			int xent = getEntity(i).getCol();
			int yent = getEntity(i).getRow();
			
			if(envi().getCellContent(xent, yent)!=getEntity(i)){
				throw new InvariantError("Entity not seen by env");
			}
		}
	}

	@Override
	public void init(EnvironmentService env) {
		//pre
		
		//run
		super.init(env);
		
		//inv post
		checkInvariants();
	}
	
	@Override
	public void removeEntity(int idx) {
		//pre
		if(idx<0 || idx >= getEntities().size()) {
			throw new PreconditionError("Invalide entity index");
		}
		
		//inv
		checkInvariants();
		
		//capture
		int size_atpre = getEntities().size();
		EntityService[] ents_atpre_bidx= new EntityService[getEntities().size() -1];
		EntityService[] ents_atpre_aidx= new EntityService[getEntities().size() -1];
		
		for(int i = 0 ; i < idx - 1; i++) {
			ents_atpre_bidx[i]=getEntity(i);
		}
		
		for(int i = idx ; i < getEntities().size()-2; i++) {
			ents_atpre_aidx[i]=getEntity(i);
		}
		
		//run
		super.removeEntity(idx);
		
		//inv post
		checkInvariants();
		
		//post
		
		//size(Entities(removeEntity(E,i))) = size(Entities(E)) - 1
		if(getEntities().size()!=size_atpre-1) {
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
		for(int k = idx ; k < getEntities().size()-2 ; k++) {
			if((getEntity(k+1))!=ents_atpre_aidx[k]) {
				throw new PostconditionError("Error removing entity removed another");
			}
		}
		
	}

	@Override
	public void addEntity(EntityService ent) {
		//pre
		
		//inv pre
		checkInvariants();
		//capture
		//TODO 
		//clone au lieu de copy comme ça
		int size_atpre = getEntities().size();
	//	System.out.println("Size at pre " + size_atpre);
		List<EntityService> ents_atpre_aidx = new ArrayList<EntityService>();
		

		for(int i = 0 ; i < size_atpre; i++) {
			ents_atpre_aidx.add(getEntity(i));
		}
		
		//run
		super.addEntity(ent);
		
		//inv post
		checkInvariants();
		
		//post
		
		//size(Entities(addEntity(E,e))) = size(Entities(E)) + 1
		if(getEntities().size()!=size_atpre + 1) {
			
			throw new PostconditionError("Error adding new entity");
		}
		
		//forall k in [0,size(Entities(E))-1], getEntity(addEntity(E,e),k)) = getEntity(E,k)
		for(int k = 0 ; k < getEntities().size()-1 ; k++) {
			if((getEntity(k))!=ents_atpre_aidx.get(k)) {
				throw new PostconditionError("Error adding entity modified another");
			}
		}
		
		//getEntity(addEntity(E,e),size(Entities(E))) = e
		if(!(getEntity(getEntities().size()-1)==ent)){
			throw new PostconditionError("Entity not added at the end.");
		}
		
	}

	@Override
	public void step() {
		//pre
		for(int i = 0 ; i < getEntities().size() -1 ; i++) {
			if(!(getEntity(i).getHP()>0)) {
				throw new PreconditionError("I see dead people");
			}
		}
		
		//inv pre
		checkInvariants();
		
		//run
		super.step();
		
		//inv post
		checkInvariants();
		
		//post
		
		
	}
	

}
