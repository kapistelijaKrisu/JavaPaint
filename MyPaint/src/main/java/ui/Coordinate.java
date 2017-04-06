
package ui;
public class Coordinate {

        public final int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Coordinate other = (Coordinate) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + this.x;
            hash = 29 * hash + this.y;
            return hash;
        }

        @Override
        public String toString() {
            return "x:" + x + " y:" + y;
        }

    }