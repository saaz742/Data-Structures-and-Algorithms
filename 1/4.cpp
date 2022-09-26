#include <iostream>

using namespace std;

int main()
{
    int n = 0;
    int max = 0;

    cin >> n;
    int nums[n];
    int p[n];
    int sum = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> nums[i];
    }
    for (int i = 0; i < n; i++)
    {
        sum += nums[i];
        p[i] = sum;
    }
    int min = 0;
    for (int j = 1; j < n; j++)
    {
        if (p[j - 1] < min)
            min = p[j - 1];
        sum = p[j] - min;
        if (sum > max)
        {
            max = sum;
        }
    }

    cout << max;
    return 0;
}
