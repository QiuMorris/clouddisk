package persional.cheneyjin.cloudisk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// @AUTHOR CHENEYJIN ZB6115110
// @EMAIL CHENEYJIN@OUTLOOK.COM
// CRC 文件校验
public class CRC {

	static public int[] Table = new int[256];

	static {
		for (int i = 0; i < 256; i++) {
			int r = i;
			for (int j = 0; j < 8; j++)
				if ((r & 1) != 0)
					r = (r >>> 1) ^ 0xEDB88320;
				else
					r >>>= 1;
			Table[i] = r;
		}
	}

	int _value = -1;

	public void Init() {
		_value = -1;
	}

	public void Update(byte[] data, int offset, int size) {
		for (int i = 0; i < size; i++)
			_value = Table[(_value ^ data[offset + i]) & 0xFF] ^ (_value >>> 8);
	}

	public void Update(byte[] data) {
		int size = data.length;
		for (int i = 0; i < size; i++)
			_value = Table[(_value ^ data[i]) & 0xFF] ^ (_value >>> 8);
	}

	public void UpdateByte(int b) {
		_value = Table[(_value ^ b) & 0xFF] ^ (_value >>> 8);
	}

	public int GetDigest() {
		return _value ^ (-1);
	}

	public static int CalCRC(String strFile) {
		int nResult = 0;
		File file = new File(strFile);
		if (!file.exists()) {
			System.out.println("文件：" + strFile + "不存在！");
			return nResult;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buff = new byte[1024];
			CRC crc = new CRC();
			crc.Init();
			while (true) {
				int nRead = fis.read(buff);
				if (nRead > 0)
					crc.Update(buff, 0, nRead);
				if (nRead < 1024)
					break;
			}
			nResult = crc.GetDigest();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nResult;
	}
}