import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Partitioner;
import java.util.Arrays;
// INCLUDE OUR LOG PACKAGES
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class WordCountLoggerPartitioner extends Partitioner<Text, IntWritable> {
    // this partitioner will break our result set up into three outputs:
    // one containing "old words"
    // one containing "timelss words"
    // everything else

    // SET UP OUR LOGGER
    private static final Logger LOGGER = Logger.getLogger(WordCountLoggerPartitioner.class.getName());
    public int getPartition(Text key, IntWritable value, int numReduceTasks) {
	// these lists would obviously, in reality, be much larger
	String[] oldWords = {"thou","doth","shall","hath","upon"};
	String[] timelessWords = {"i","love","you"};
	// initialize our reducer number to the default (everything else)
	Integer reducerNumber = 0;

	// IN THIS CODE, OUR ".contains(key)" CODE IS WRONG.. IT DOES NOT
	// CONVERT THE key TO A STRING. IF WE WANTED TO MAKE SURE OUR KEYS
	// WERE COMING THROUGH. THIS WILL RESULT IN A TON OF DATA!!!
	LOGGER.warn("WE HAVE A VALID KEY:"+key);
	// hardcoded and ugly, but just to illustrate the running of this code
	if(Arrays.asList(oldWords).contains(key)) {
	    // old words get another reducer
	    reducerNumber = 1;
	} else if(Arrays.asList(timelessWords).contains(key)) {
	    // timeless words get routed to yet another reducer
	    reducerNumber = 2;
	}
	// function just returns
	return reducerNumber;
    }
}