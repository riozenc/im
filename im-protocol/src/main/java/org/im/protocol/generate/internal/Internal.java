/**
 * @Project:im-protocol
 * @Title:Internal.java
 * @Author:Riozenc
 * @Datetime:2017年7月17日 下午8:25:11
 * 
 */
package org.im.protocol.generate.internal;

import org.im.protocol.generate.internal.Internal.Greet.From;

public final class Internal {

	public interface GreetOrBuilder {
		boolean hasFrom();

		From getFrom();
	}

	/**
	 * Protobuf type {@code Greet}
	 *
	 * <pre>
	 * 服务间建立连接时发送的协议
	 * </pre>
	 */
	public static final class Greet {

		public static Builder newBuilder() {
			return Builder.create();
		}

		public static final class Builder {
			private int bitField0_;
			private From from_ = From.Logic;

			private static Builder create() {
				return new Builder();
			}

			public From getFrom() {
				return from_;
			}

			public Builder setFrom(From value) {
				if (value == null) {
					throw new NullPointerException();
				}
				bitField0_ |= 0x00000001;
				from_ = value;
				return this;
			}

			public Builder clearFrom() {
				bitField0_ = (bitField0_ & ~0x00000001);
				from_ = From.Logic;
				return this;
			}

		}

		/**
		 * Protobuf enum {@code Greet.From}
		 */
		public enum From {
			/**
			 * <code>Logic = 1;</code>
			 */
			Logic(0, 1),
			/**
			 * <code>Gate = 2;</code>
			 */
			Gate(1, 2),
			/**
			 * <code>Auth = 3;</code>
			 */
			Auth(2, 3),;

			/**
			 * <code>Logic = 1;</code>
			 */
			public static final int Logic_VALUE = 1;
			/**
			 * <code>Gate = 2;</code>
			 */
			public static final int Gate_VALUE = 2;
			/**
			 * <code>Auth = 3;</code>
			 */
			public static final int Auth_VALUE = 3;

			public final int getNumber() {
				return value;
			}

			public static From valueOf(int value) {
				switch (value) {
				case 1:
					return Logic;
				case 2:
					return Gate;
				case 3:
					return Auth;
				default:
					return null;
				}
			}

			private final int index;
			private final int value;

			private From(int index, int value) {
				this.index = index;
				this.value = value;
			}
		}
	}

}
