package org.acz.jlzop;

import com.google.common.io.ByteStreams;
import com.hadoop.compression.lzo.LzopCodec;
import org.apache.hadoop.io.compress.Decompressor;

import java.io.InputStream;

public class Main
{
    public static void main(String[] args)
            throws Exception
    {
        Decompressor decompressor = new LzopCodec.LzopDecompressor(256 * 1024);
        InputStream stream = (InputStream) LzopCodec.class.getClassLoader()
                .loadClass("com.hadoop.compression.lzo.LzopCodec$LzopInputStream")
                .getConstructor(InputStream.class, Decompressor.class, Integer.TYPE)
                .newInstance(System.in, decompressor, 256 * 1024);
        ByteStreams.copy(stream, System.out);
    }
}
