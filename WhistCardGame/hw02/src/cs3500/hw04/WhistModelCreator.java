package cs3500.hw04;

import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;

/**
 * Created by ErinZhang on 2/19/16.
 */
public class WhistModelCreator {

  public enum ModelType {TRUMP, NOTRUMP}

  public static CardGameModel create(ModelType model) {

    if (model.equals(ModelType.TRUMP)) {
      return new TrumpModel();
    } else if (model.equals(ModelType.NOTRUMP)) {
      return new WhistModel();
    } else {
      return null;
    }
  }

}
