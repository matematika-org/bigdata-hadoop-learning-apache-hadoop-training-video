import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GSOD {
    public static void main(String[] args) throws Exception {
	Job job = new Job();
        job.setJobName("GSOD reformatter");

        //setting the class names
        job.setJarByClass(GSOD.class);
        job.setMapperClass(GSODMapper.class);
        job.setReducerClass(GSODReducer.class);

        //setting the output data type classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //to accept the hdfs input and outpur dir at run time
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
	System.exit(success ? 0 : 1);
    }
}