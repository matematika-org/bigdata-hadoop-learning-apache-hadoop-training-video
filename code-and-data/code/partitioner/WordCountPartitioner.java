import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Partitioner;
import java.util.Arrays;

public class WordCountPartitioner extends Partitioner<Text, IntWritable> {
    // this partitioner will break our result set up into three outputs:
    // one containing "old words"
    // one containing "timelss words"
    // everything else

    public int getPartition(Text key, IntWritable value, int numReduceTasks) {
	// these lists would obviously, in reality, be much larger
	String[] oldWords = {"thou","doth","shall","hath","upon"};
	String[] timelessWords = {"i","love","you"};
	// initialize our reducer number to the default (everything else)
	Integer reducerNumber = 0;

	// hardcoded and ugly, but just to illustrate the running of this code
	if(Arrays.asList(oldWords).contains(key.toString())) {
	    // old words get another reducer
	    reducerNumber = 1;
	} else if(Arrays.asList(timelessWords).contains(key.toString())) {
	    // timeless words get routed to yet another reducer
	    reducerNumber = 2;
	}
	// function just returns
	return reducerNumber;
    }
}