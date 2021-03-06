package seu.api.launch;

import seu.annotation.router.MergeLaunch;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 一定要在java工程下，不然AbstractProcessor会无法使用
 * apt需要使用jdk下的包，而这个在androidsdk中是没有的。
 * Created by wuxiangyu on 2017/7/3.
 * https://lizhaoxuan.github.io/2016/07/17/apt-run_demo/
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class MergeProcessor extends AbstractProcessor {
    private Filer mFileUtils;//文件相关的辅助类，生成javasourcecode
    private Elements mElementUtils;//和元素相关的辅助类，获取元素信息
    private Messager mMessager;//和日志相关的辅助类
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }
    /**
     * 表示该Processor处理哪些注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(MergeLaunch.class.getCanonicalName());//区别：getName，遇到数组的时候回不一样，[[String
        return types;
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //用来输出到控制台，在编译器下方的Messages窗口
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "MergeProcessor-----------------------------------");
        //返回true表示该Process已经处理了，其他的Process不需要再处理了。
        MergelaunchInfo mergelaunchInfo = new MergelaunchInfo();
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(MergeLaunch.class);
//        if (elementSet == null || elementSet.size() <= 1) {
//            return false;
//        }

//        for (Element element : elementSet) {
//            TypeElement typeElement = (TypeElement) element;
//            String qualifiedName = typeElement.getQualifiedName().toString();
//            messager.printMessage(Diagnostic.Kind.NOTE, "MergeProcessor-----------------------------------+:" + qualifiedName);
//            mergelaunchInfo.launcUtilList.add(qualifiedName);
//
//        }
//        try {
//            JavaFileObject sourceFile = mFileUtils.createSourceFile(MergelaunchInfo.PACKAGE_NAME + "." + MergelaunchInfo.CLASS_NAME);
//            Writer writer = sourceFile.openWriter();
//            writer.write(mergelaunchInfo.getJavaCode());
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//
//        }
        return true;
    }
}
