public class LW3_task2 {
	public static class Point {
		private double x;
		private double y;
		private double z;
		private double time;

		public Point(double x, double y, double z, double time) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.time = time;
		}

		public Point add(Point other) {
			return new Point(this.x + other.x, this.y + other.y, this.z + other.z, this.time);
		}

		public Point subtract(Point other) {
			return new Point(this.x - other.x, this.y - other.y, this.z - other.z, this.time);
		}

		public Point multiply(double scalar) {
			return new Point(this.x * scalar, this.y * scalar, this.z * scalar, this.time);
		}

		public Point divide(double scalar) {
			if (scalar != 0) {
				return new Point(this.x / scalar, this.y / scalar, this.z / scalar, this.time);
			} else {
				System.out.println("Cannot divide by zero.");
				return null;
			}
		}

		public void move(double deltaX, double deltaY, double deltaZ, double deltaTime) {
			this.x += deltaX;
			this.y += deltaY;
			this.z += deltaZ;
			this.time += deltaTime;
		}

		public Point calculateVelocity(Point previousPoint) {
			double deltaTime = this.time - previousPoint.time;

			if (deltaTime != 0) {
				double velocityX = (this.x - previousPoint.x) / deltaTime;
				double velocityY = (this.y - previousPoint.y) / deltaTime;
				double velocityZ = (this.z - previousPoint.z) / deltaTime;

				return new Point(velocityX, velocityY, velocityZ, this.time);
			} else {
				System.out.println("Cannot calculate velocity with zero time difference.");
				return null;
			}
		}

		public Point calculateAcceleration(Point previousVelocity, Point previousPoint) {
			double deltaTime = this.time - previousPoint.time;

			if (deltaTime != 0) {
				Point currentVelocity = calculateVelocity(previousPoint);
				if (currentVelocity != null) {
					double accelerationX = (currentVelocity.x - previousVelocity.x) / deltaTime;
					double accelerationY = (currentVelocity.y - previousVelocity.y) / deltaTime;
					double accelerationZ = (currentVelocity.z - previousVelocity.z) / deltaTime;

					return new Point(accelerationX, accelerationY, accelerationZ, this.time);
				}
			}
			return null;
		}

		public double calculateDistance(Point other) {
			double deltaX = this.x - other.x;
			double deltaY = this.y - other.y;
			double deltaZ = this.z - other.z;

			return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		}

		public static boolean canIntersect(Point point1, Point point2, double tolerance) {
			return Math.abs(point1.time - point2.time) < tolerance;
		}

		@Override
		public String toString() {
			return "Point{" +
					"x=" + x +
					", y=" + y +
					", z=" + z +
					", time=" + time +
					'}';
		}
	}

	public static void main(String[] args) {
		Point startPoint = new Point(0, 0, 0, 0);
		Point endPoint = new Point(5, 5, 5, 2);

		System.out.println("Initial Point: " + startPoint);
		System.out.println("End Point: " + endPoint);

		Point result = startPoint.add(endPoint);
		System.out.println("Addition: " + result);

		result = endPoint.subtract(startPoint);
		System.out.println("Subtraction: " + result);

		result = startPoint.multiply(2);
		System.out.println("Multiplication: " + result);

		result = endPoint.divide(2);
		System.out.println("Division: " + result);

		startPoint.move(1, 1, 1, 1);
		System.out.println("After Move: " + startPoint);

		Point previousPoint = new Point(0, 0, 0, 0);
		previousPoint.move(2, 2, 2, 1);

		Point previousVelocity = new Point(2, 2, 2, 1);

		Point velocity = endPoint.calculateVelocity(previousPoint);
		System.out.println("Velocity: " + velocity);

		Point acceleration = endPoint.calculateAcceleration(previousVelocity, previousPoint);
		System.out.println("Acceleration: " + acceleration);

		double distance = startPoint.calculateDistance(endPoint);
		System.out.println("Distance: " + distance);

		boolean canIntersect = Point.canIntersect(startPoint, endPoint, 0.1);
		System.out.println("Can intersect: " + canIntersect);
	}
}
