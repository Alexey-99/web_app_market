package by.koroza.zoo_market.model.entity.market.product.constituent;

import java.util.Arrays;

public class ImageFile {
	private String name;
	private byte[] bytes;

	public ImageFile() {
		this.bytes = null;
		this.name = null;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (this.bytes != null ? Arrays.hashCode(this.bytes) : 1);
		result = PRIME * result + (this.name != null ? this.name.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		ImageFile otherImageFile = (ImageFile) object;
		if (!Arrays.equals(this.bytes, otherImageFile.bytes)) {
			return false;
		}
		if (this.name == null) {
			if (otherImageFile.name != null) {
				return false;
			}
		} else if (!this.name.equals(otherImageFile.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageFile [name=");
		builder.append(name);
		builder.append(", bytes=");
		builder.append(Arrays.toString(bytes));
		builder.append("]");
		return builder.toString();
	}

	public static class ImageFileBuilder {
		private ImageFile imageFile;

		public ImageFileBuilder() {
			this.imageFile = new ImageFile();
		}

		public ImageFileBuilder setName(String name) {
			this.imageFile.setName(name);
			return this;
		}

		public ImageFileBuilder setBytes(byte[] bytes) {
			this.imageFile.setBytes(bytes);
			return this;
		}

		public ImageFile build() {
			return this.imageFile;
		}
	}
}