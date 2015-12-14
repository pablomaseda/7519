package controller;


import java.io.IOException;


import model.LotkaVolterra;
import uchicago.src.sim.analysis.DataSource;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Controller;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.engine.SimModelImpl;


public class ModelController2 extends SimModelImpl {
	
	private static final String TITULO_TP = "75.19 - TP Lotka Volterra"; 
	private static final int INITIAL_PREYCOUNT = 1;
	private static final int INITIAL_PREDATORCOUNT = 1;
	
	private static final double PREYS_GROWTH = 1;
	private static final double PREDATION_SPEED = 0.2;
	private static final double PREDATORS_GROWTH = 0.5;
	private static final double PREDATORS_DEATH = 2;
	
	private int preysCount = INITIAL_PREYCOUNT; 
    private int predatorsCount = INITIAL_PREDATORCOUNT;
    private double preysGrowth = PREYS_GROWTH;
    private double predationSpeed = PREDATION_SPEED; 
    private double predatorsGrowth = PREDATORS_GROWTH;
    private double predatorsDeath = PREDATORS_DEATH;
    
    private LotkaVolterra model;
    private Schedule schedule;
    
    private OpenSequenceGraph evolucionEspecies;

	@Override
	public void begin() {
		try{
            buildModel();
            buildSchedule();
            buildDisplay();

            evolucionEspecies.display();
            
            
        } catch (RuntimeException e){
            stop();
            e.printStackTrace();
        }
	}

	@Override
	public String[] getInitParam() {
		String[] initParams =
	        {  "PreysCount","PredatorsCount","PreysGrowth","PredationSpeed","PredatorsGrowth","PredatorsDeath"
	        };
	        return initParams;
	}

	@Override
	public String getName() {
		
		return TITULO_TP;
	}

	@Override
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void buildModel(){
        try {
			this.model = new LotkaVolterra(preysCount, predatorsCount, preysGrowth,predationSpeed, predatorsGrowth, predatorsDeath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	/**
     * Clase interna para los contadores de los graficos -----------------------------------------
     * - Presas en total
     * @author pali
     *
     */
    class PreyInSpace implements DataSource, Sequence
    {       
        public double getSValue()
        {
            return (double)getModel().getPreysCount();
        }

        public Object execute()
        {
            return new Double(getSValue());
        }   
    }

    /**
     * Clase interna para los contadores de los graficos -----------------------------------------
     * - Depredadores en total
     *
     */
    class PredatorInSpace implements DataSource, Sequence
    {       
        public double getSValue()
        {
            return (double)getModel().getPredatorsCount();
        }

        public Object execute()
        {
            return new Double(getSValue());
        }   
    }

	@Override
	public void setup() {
		
			Controller.ALPHA_ORDER = false;
		       
	        schedule = new Schedule(1);
	        
	        // Destruir 
	        if (evolucionEspecies != null) 
	            evolucionEspecies.dispose();
	      
	        evolucionEspecies = null;
	        
	     // Creamos Displays
	        evolucionEspecies = new OpenSequenceGraph("Numero de agentes en el espacio", this);
	        
	     // Registramos Displays
	        this.registerMediaProducer("Plot", evolucionEspecies);
						
		
	}
	
	public void buildDisplay(){

        evolucionEspecies.addSequence("Presas totales", new PreyInSpace(), java.awt.Color.black, 0);
        evolucionEspecies.addSequence("Depredadores totales", new PredatorInSpace(), java.awt.Color.red, 0);

    }
	
    /**
     * 
     */
    public void buildSchedule()
    {
  
        class AccionTick extends BasicAction
        {
            public void execute()
            {
            	try {
					getModel().update();
					           
	                System.out.print("Presa: " + getModel().getPreysCount()+ " ");
	                System.out.println("Depredador: " + getModel().getPredatorsCount() + " ");
	                
	                
				} catch (IOException e) {
					e.printStackTrace();
					stop();
				}
            	
            	if(getTickCount() > 500){
                	stop();
                }
            	
                
            }
        }
        schedule.scheduleActionAtInterval(1, new AccionTick());

        class AccionGrafico extends BasicAction
        {
            public void execute()
            {
                evolucionEspecies.step();
            }
        }
        schedule.scheduleActionAtInterval(1, new AccionGrafico());

        
    }
	
	  /**
     * METODO PRINCIPAL ------------------------------------------------------
     * @param args
     */
    /*
    public static void main(String[] args)
    {
    	 SimInit init = new SimInit();
        ModelController2 model = new ModelController2();
        init.loadModel(model, "", false); 
       
    }*/

	public int getPreysCount() {
		return preysCount;
		
	}

	public void setPreysCount(int preysCount) {
		this.preysCount = preysCount;
	}

	public int getPredatorsCount() {
		return predatorsCount;
		
	}

	public void setPredatorsCount(int predatorsCount) {
		this.predatorsCount = predatorsCount;
	}

	public double getPreysGrowth() {
		return preysGrowth;
	}

	public void setPreysGrowth(double preysGrowth) {
		this.preysGrowth = preysGrowth;
	}

	public double getPredationSpeed() {
		return predationSpeed;
	}

	public void setPredationSpeed(double predationSpeed) {
		this.predationSpeed = predationSpeed;
	}

	public double getPredatorsGrowth() {
		return predatorsGrowth;
	}

	public void setPredatorsGrowth(double predatorsGrowth) {
		this.predatorsGrowth = predatorsGrowth;
	}

	public double getPredatorsDeath() {
		return predatorsDeath;
	}

	public void setPredatorsDeath(double predatorsDeath) {
		this.predatorsDeath = predatorsDeath;
	}

	public LotkaVolterra getModel() {
		return model;
	}

	public void setModel(LotkaVolterra model) {
		this.model = model;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}
