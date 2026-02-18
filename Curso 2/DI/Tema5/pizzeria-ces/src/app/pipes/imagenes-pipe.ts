import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    switch(value) {
      case 1:
        return ""
      case 2:
        return ""
    }

    return ""

  }

}
