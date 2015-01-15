package cn.bvin.lib.debug;



/**
 * The Class StackTracer.
 */
public class StackTracer {

	/**
	 * Gets the stack trace lines.
	 *
	 * @return the stack trace lines
	 */
	public static String getStackTraceLines(){
		
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();  
        if(sts == null)  
        {  
            return null;  
        }  
        for(StackTraceElement st : sts)  //perform only one time
        {  
            if(st.isNativeMethod())  
            {  
                continue;  //不能是native方法
            }  
            if(st.getClassName().equals(Thread.class.getName()))  
            {  
                continue;  //不能是线程
            }  
            if(st.getClassName().equals(StackTracer.class.getName()))  
            {  
                continue;  //不能是StackTracer
            }  
            if(st.getClassName().equals(DebugeHelper.class.getName()))  
            {  
                continue;  //不能是DebugeHelper
            }  
            return  st.getLineNumber() + "#" + st.getFileName() + "/" + st.getMethodName();  
        }  
        return null;  
	}
	
	/**
	 * Gets the calling info.
	 *
	 * @return the calling info
	 */
	public static String getCallingInfo(){
		StackTraceElement[]  trace = new Throwable().fillInStackTrace().getStackTrace();
		StringBuilder line = new StringBuilder();
		for (int i = 2; i < trace.length; i++) {
			Class<?> clazz = trace.getClass();
			if(!clazz.equals(DebugeHelper.class)){
				String callingClass = trace[i].getClassName();//被调类
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                line.append(callingClass+".");
                line.append(trace[i].getMethodName()+"#");
                line.append(trace[i].getLineNumber());
                break;
			}
		}
		return line.toString();
		
	}
	
	/**
	 * Gets the current thread info.
	 *
	 * @return the current thread info
	 */
	public static String getCurrentThreadInfo(){
		return Thread.currentThread().getName()+" thread "
		+"id:"+Thread.currentThread().getId()
		+" leve"+Thread.currentThread().getPriority()
		+" run "+Thread.currentThread().isAlive();
	}
	
}
