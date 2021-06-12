import java.io.IOException;

/**
 * This class represents a mock appendable, where all the methods throw IOExceptions. It exists
 * solely for the purpose of testing.
 */
public class MockAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("");
  }
}
