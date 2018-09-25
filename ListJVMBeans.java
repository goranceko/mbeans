import javax.management.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ListJVMBeans {

    public static void main(String[] args) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("jvm-mbeans"));
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            Set names = mBeanServer.queryNames(null, null);
            for (Object mbeanName : names) {
                ObjectName objectName = (ObjectName) mbeanName;
                writer.write("MBean: " + objectName + "\n");
                MBeanAttributeInfo[] attributes = mBeanServer.getMBeanInfo(objectName).getAttributes();
                String list = Arrays.asList(attributes).stream().map(info -> info.getName()).collect(Collectors.joining(","));
                writer.write("Attributes: " + list + "\n\n");
            }
            writer.close();
        } catch (InstanceNotFoundException | IntrospectionException | ReflectionException e) {
            e.printStackTrace();
        }
    }
}
