package model;

import java.io.IOException;

public class LotkaVolterra {
  private double preysCount;
  private double predatorsCount;

  private double preysGrowth;
  private double predationSpeed;
  private double predatorsGrowth;
  private double predatorsDeath;
  private int seconds;

  public LotkaVolterra(int preysCount, int predatorsCount, double preysGrowth,
                       double predationSpeed, double predatorsGrowth, double predatorsDeath) throws IOException {
    this.preysCount = preysCount;
    this.predatorsCount = predatorsCount;

    this.preysGrowth = preysGrowth;
    this.predationSpeed = predationSpeed;
    this.predatorsGrowth = predatorsGrowth;
    this.predatorsDeath = predatorsDeath;
    this.seconds = 0;
  }

  public void update() throws IOException {
    seconds++;
    double h = 0.1;
    preysCount = preysCount + h * preysCount * (preysGrowth - predationSpeed * predatorsCount);
    predatorsCount = predatorsCount + h * predatorsCount * (predatorsGrowth * preysCount - predatorsDeath);
    //System.out.println("Preys: "+preysCount+"--"+"Predators: "+predatorsCount+"--"+seconds);
  }

  public double getPreysCount() {
    return preysCount;
  }

  public double getPredatorsCount() {
    return predatorsCount;
  }

  public int getSeconds() {
    return this.seconds;
  }
}
