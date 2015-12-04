package model;

public class DoubleLotkaVolterra {
  private double preysCount;
  private double predatorsACount;
  private double predatorsBCount;

  private double preysGrowth;

  private double predationASpeed;
  private double predatorsAGrowth;
  private double predatorsADeath;

  private double predationBSpeed;
  private double predatorsBGrowth;
  private double predatorsBDeath;

  public DoubleLotkaVolterra(int preysCount, int predatorsACount, int predatorsBCount, double preysGrowth,
                             double predationASpeed, double predatorsAGrowth, double predatorsADeath,
                             double predationBSpeed, double predatorsBGrowth, double predatorsBDeath) {
    this.preysCount = preysCount;
    this.predatorsACount = predatorsACount;
    this.predatorsBCount = predatorsBCount;

    this.preysGrowth = preysGrowth;

    this.predationASpeed = predationASpeed;
    this.predatorsAGrowth = predatorsAGrowth;
    this.predatorsADeath = predatorsADeath;

    this.predationBSpeed = predationBSpeed;
    this.predatorsBGrowth = predatorsBGrowth;
    this.predatorsBDeath = predatorsBDeath;
  }

  public void update() {
    double h = 0.1;

    // Euler's ODE method
    preysCount = preysCount + h * preysCount * (preysGrowth - predationASpeed * predatorsACount);
    predatorsACount = predatorsACount + h * predatorsACount * (predatorsAGrowth * preysCount - predatorsADeath - predationBSpeed * predatorsBCount);
    predatorsBCount = predatorsBCount + h * predatorsBCount * (predatorsBGrowth * predatorsACount - predatorsBDeath);
  }

  public double getPreysCount() {
    return preysCount;
  }

  public double getPredatorsACount() {
    return predatorsACount;
  }

  public double getPredatorsBCount() {
    return predatorsBCount;
  }
}